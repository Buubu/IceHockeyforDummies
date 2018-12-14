package bum.icehockeyfordummies.user_interface;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;
import bum.icehockeyfordummies.R;
import bum.icehockeyfordummies.database.ClubEntity;
import bum.icehockeyfordummies.viewmodels.ClubsListViewModel;


public class ClubsFragment extends Fragment {

    private static final String TAG = "ClubsFragment";
    private List<ClubEntity> clubs;
    private ClubsListViewModel viewModel;


    // Constructor of the new instance
    public static ClubsFragment newInstance() {
        return (new ClubsFragment());
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_clubs, container, false);

        RecyclerView recycler = view.findViewById(R.id.recycler_clubs);
        recycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        recycler.setAdapter(new RecyclerViewAdapter());

        return view;
    }


    private class RecyclerViewHolder extends RecyclerView.ViewHolder {
        private CardView cardView;
        private ImageView clubLogo;
        private TextView clubName;
        private ImageButton clubFavorite;


        public RecyclerViewHolder(View itemView) {
            super(itemView);
        }

        public RecyclerViewHolder(LayoutInflater inflater, ViewGroup container) {
            super(inflater.inflate(R.layout.items_clubs, container, false));

            cardView = itemView.findViewById(R.id.cardview);
            clubLogo = itemView.findViewById(R.id.club_logo);
            clubName = itemView.findViewById(R.id.club_name);
            clubFavorite = itemView.findViewById(R.id.club_favorite);
        }
    }


    private class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewHolder> {

        @Override
        public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int i) {
            LayoutInflater inflater = LayoutInflater.from(getActivity());

            return new RecyclerViewHolder(inflater, parent);
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerViewHolder recyclerViewHolder, int i) {

        }

        @Override
        public int getItemCount() {
            return 5;
        }
    }

}








//    private RecyclerAdapter<AccountEntity> mAdapter;
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        getLayoutInflater().inflate(R.layout.activity_accounts, frameLayout);
//
//        setTitle(getString(R.string.title_activity_accounts));
//        navigationView.setCheckedItem(position);
//
//        RecyclerView recyclerView = findViewById(R.id.accountsRecyclerView);
//
//        // use this setting to improve performance if you know that changes
//        // in content do not change the layout size of the RecyclerView
//        //mRecyclerView.setHasFixedSize(true);
//
//        // use a linear layout manager
//        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
//        recyclerView.setLayoutManager(layoutManager);
//
//        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
//                LinearLayoutManager.VERTICAL);
//        recyclerView.addItemDecoration(dividerItemDecoration);
//
//        SharedPreferences settings = getSharedPreferences(BaseActivity.PREFS_NAME, 0);
//        String user = settings.getString(BaseActivity.PREFS_USER, null);
//
//        mAccounts = new ArrayList<>();
//        mAdapter = new RecyclerAdapter<>(new RecyclerViewItemClickListener() {
//            @Override
//            public void onItemClick(View v, int position) {
//                Log.d(TAG, "clicked position:" + position);
//                Log.d(TAG, "clicked on: " + mAccounts.get(position).getName());
//
//                Intent intent = new Intent(AccountsActivity.this, AccountDetailActivity.class);
//                intent.setFlags(
//                        Intent.FLAG_ACTIVITY_NO_ANIMATION |
//                                Intent.FLAG_ACTIVITY_NO_HISTORY
//                );
//                intent.putExtra("accountId", mAccounts.get(position).getId());
//                startActivity(intent);
//            }
//
//            @Override
//            public void onItemLongClick(View v, int position) {
//                Log.d(TAG, "longClicked position:" + position);
//                Log.d(TAG, "longClicked on: " + mAccounts.get(position).getName());
//
//                createDeleteDialog(position);
//            }
//        });
//
//        FloatingActionButton fab = findViewById(R.id.floatingActionButton);
//        fab.setOnClickListener(view -> {
//                    Intent intent = new Intent(AccountsActivity.this, EditAccountActivity.class);
//                    intent.setFlags(
//                            Intent.FLAG_ACTIVITY_NO_ANIMATION |
//                                    Intent.FLAG_ACTIVITY_NO_HISTORY
//                    );
//                    startActivity(intent);
//                }
//        );
//
//        AccountListViewModel.Factory factory = new AccountListViewModel.Factory(
//                getApplication(), user);
//        mViewModel = ViewModelProviders.of(this, factory).get(AccountListViewModel.class);
//        mViewModel.getOwnAccounts().observe(this, accountEntities -> {
//            if (accountEntities != null) {
//                mAccounts = accountEntities;
//                mAdapter.setData(mAccounts);
//            }
//        });
//
//        recyclerView.setAdapter(mAdapter);
//    }
//
//    @Override
//    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//        if (item.getItemId() == BaseActivity.position) {
//            drawerLayout.closeDrawer(GravityCompat.START);
//            return false;
//        }
//        /*
//        The activity has to be finished manually in order to guarantee the navigation hierarchy working.
//        */
//        finish();
//        return super.onNavigationItemSelected(item);
//    }
//
//    private void createDeleteDialog(final int position) {
//        final AccountEntity account = mAccounts.get(position);
//        LayoutInflater inflater = LayoutInflater.from(this);
//        final View view = inflater.inflate(R.layout.row_delete_item, null);
//        final AlertDialog alertDialog = new AlertDialog.Builder(this).create();
//        alertDialog.setTitle(getString(R.string.title_activity_delete_account));
//        alertDialog.setCancelable(false);
//
//        final TextView deleteMessage = view.findViewById(R.id.tv_delete_item);
//        deleteMessage.setText(String.format(getString(R.string.account_delete_msg), account.getName()));
//
//        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, getString(R.string.action_accept), (dialog, which) -> {
//            Toast toast = Toast.makeText(this, getString(R.string.account_deleted), Toast.LENGTH_LONG);
//            mViewModel.deleteAccount(account);
//            toast.show();
//        });
//
//        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, getString(R.string.action_cancel), (dialog, which) -> alertDialog.dismiss());
//        alertDialog.setView(view);
//        alertDialog.show();
//    }
//}
