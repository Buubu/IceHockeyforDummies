//package bum.icehockeyfordummies.user_interface;
//
//import android.app.Application;
//import android.arch.lifecycle.ViewModelProviders;
//import android.content.Intent;
//import android.os.Bundle;
//import android.support.v4.app.Fragment;
//import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.RecyclerView;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import java.util.ArrayList;
//import java.util.List;
//import bum.icehockeyfordummies.adapters.ClubItemClickListener;
//import bum.icehockeyfordummies.adapters.ClubsAdapter;
//import bum.icehockeyfordummies.R;
//import bum.icehockeyfordummies.database.ClubEntity;
//import bum.icehockeyfordummies.database.ClubRepository;
//import bum.icehockeyfordummies.firebase.ClubsLiveData;
//import bum.icehockeyfordummies.viewmodels.ClubsListViewModel;
//
//
//public class ClubsFragment extends Fragment {
//    private static final String TAG = "ClubsFragment";
//    private List<ClubEntity> clubs;
//    private ClubsAdapter adapter;
//    private ClubsListViewModel viewModel;
//    private Application app;
//
//
//    // Constructor of the new instance
//    public static ClubsFragment newInstance() {
//        return (new ClubsFragment());
//    }
//
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.fragment_clubs, container, false);
//        getActivity().setTitle(R.string.clubs);
//
//        RecyclerView recycler = view.findViewById(R.id.recycler_clubs);
//        recycler.setLayoutManager(new LinearLayoutManager(getActivity()));
//
//
//        clubs = new ArrayList<>();
//        adapter = new ClubsAdapter(new ClubItemClickListener() {
//            public void onItemClick(View view, int position) {
//                Log.d(TAG, "clicked position: " + position);
//                Log.d(TAG, "clicked on: " + clubs.get(position).getName());
//
////                Intent intent = new Intent(ClubsFragment.this, ClubActivity.class);
////                intent.putExtra("id", clubs.get(position).getId());
////                startActivity(intent);
//            }
//
//            public void onItemLongClick(View view, int position) {
//                Log.d(TAG, "long click position: " + position);
//                Log.d(TAG, "long click on: " + clubs.get(position).getName());
//
//                createDeleteDialog(position);
//            }
//        });
//
//
//        ClubsListViewModel.Factory factory = new ClubsListViewModel.Factory(
//                getActivity().getApplication());
//
//        viewModel = ViewModelProviders.of(this, factory).get(ClubsListViewModel.class);
//        viewModel.getAllClubs().observe(this, clubEntities -> {
//            if (clubEntities != null) {
//                clubs = clubEntities;
//                adapter.setData(clubs);
//            }
//        });
//
//        recycler.setAdapter(adapter);
//        return view;
//    }
//
//
//    private void createDeleteDialog(final int position) {
////        final AccountEntity account = mAccounts.get(position);
////        LayoutInflater inflater = LayoutInflater.from(this);
////        final View view = inflater.inflate(R.layout.row_delete_item, null);
////        final AlertDialog alertDialog = new AlertDialog.Builder(this).create();
////        alertDialog.setTitle(getString(R.string.title_activity_delete_account));
////        alertDialog.setCancelable(false);
////
////        final TextView deleteMessage = view.findViewById(R.id.tv_delete_item);
////        deleteMessage.setText(String.format(getString(R.string.account_delete_msg), account.getName()));
////
////        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, getString(R.string.action_accept), (dialog, which) -> {
////            Toast toast = Toast.makeText(this, getString(R.string.account_deleted), Toast.LENGTH_LONG);
////            mViewModel.deleteAccount(account);
////            toast.show();
////        });
////
////        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, getString(R.string.action_cancel), (dialog, which) -> alertDialog.dismiss());
////        alertDialog.setView(view);
////        alertDialog.show();
//    }
//}
