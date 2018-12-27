package bum.icehockeyfordummies.adapters;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;
import bum.icehockeyfordummies.R;
import bum.icehockeyfordummies.database.ClubEntity;


public class ClubsAdapter extends RecyclerView.Adapter<ClubsAdapter.ClubsHolder> {

    private List<ClubEntity> data;
    private ClubItemClickListener listener;


    // Class ClubsHolder
    static class ClubsHolder extends RecyclerView.ViewHolder {
        private CardView cardView;
        private ImageView clubLogo;
        private TextView clubName;
        private ImageButton clubFavorite;

        public ClubsHolder(View itemView) {
            super(itemView);
        }

        public ClubsHolder(LayoutInflater inflater, ViewGroup container) {
            super(inflater.inflate(R.layout.items_clubs, container, false));

            cardView = itemView.findViewById(R.id.cardview);
            clubLogo = itemView.findViewById(R.id.club_logo);
            clubName = itemView.findViewById(R.id.club_name);
            clubFavorite = itemView.findViewById(R.id.club_favorite);
        }
    }


    public ClubsAdapter(ClubItemClickListener listener) {
        this.listener = listener;
    }


    public ClubsAdapter.ClubsHolder onCreateViewHolder(ViewGroup parent, int type) {
        View view = (View) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.items_clubs, parent, false);

        final ClubsHolder holder = new ClubsHolder(view);
        view.setOnClickListener(v -> listener.onItemClick(view, holder.getAdapterPosition()));
        view.setOnLongClickListener(v -> {
            listener.onItemLongClick(view, holder.getAdapterPosition());
            return true;
        });

        return holder;
    }


    public void onBindViewHolder(ClubsAdapter.ClubsHolder holder, int position) {
//        ClubEntity item = data.get(position);


        // SET THE VALUE OF EACH

        // Set the correct data to each card in the view
//        holder.clubName.setText(item.getName());

        //recyclerViewHolder.clubLogo
        //recyclerViewHolder.clubName.setText(clubs.get(position).getNameClub());
        //recyclerViewHolder.clubFavorite


    }


    // Number of cards to show (related to the number of clubs)
    public int getItemCount() {
        return 5;
//        if (data != null) {
//            return data.size();
//        } else {
//            return 0;
//        }
    }


    // Set the data
    public void setData(final List<ClubEntity> clubs) {
        data = clubs;
    }
}
