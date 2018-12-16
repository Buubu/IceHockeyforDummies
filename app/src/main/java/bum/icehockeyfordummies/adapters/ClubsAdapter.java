package bum.icehockeyfordummies.adapters;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import bum.icehockeyfordummies.R;


public class ClubsAdapter extends RecyclerView.Adapter<ClubsAdapter.ClubsHolder> {

    public ClubsHolder onCreateViewHolder(ViewGroup parent, int i) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new ClubsHolder(inflater, parent);
    }

    
    // Set the correct data to each card in the view
    public void onBindViewHolder(ClubsHolder recyclerViewHolder, int position) {

        // SET THE VALUE OF EACH
        //recyclerViewHolder.clubLogo
        //recyclerViewHolder.clubName.setText(clubs.get(position).getNameClub());
        //recyclerViewHolder.clubFavorite
    }


    // Number of cards to show (related to the number of clubs)
    public int getItemCount() {
        return 5;
    }


    // Class ClubsHolder
    class ClubsHolder extends RecyclerView.ViewHolder {
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
}
