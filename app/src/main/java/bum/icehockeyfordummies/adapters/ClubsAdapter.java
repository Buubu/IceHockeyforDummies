package bum.icehockeyfordummies.adapters;

import android.support.v7.util.DiffUtil;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;
import java.util.Objects;
import bum.icehockeyfordummies.R;
import bum.icehockeyfordummies.database.ClubEntity;


public class ClubsAdapter extends RecyclerView.Adapter<ClubsAdapter.ClubsHolder> {
    private List<ClubEntity> data;
    private ItemClickListener listener;


    // Class ClubsHolder
    public class ClubsHolder extends RecyclerView.ViewHolder {
        private ImageView clubLogo;
        private TextView clubName;
        private ImageView clubFavorite;

        public ClubsHolder(View itemView) {
            super(itemView);

            clubLogo = itemView.findViewById(R.id.club_logo);
            clubName = itemView.findViewById(R.id.club_name);
            clubFavorite = itemView.findViewById(R.id.club_favorite);
        }
    }


    // Define the adapter
    public ClubsAdapter(ItemClickListener listener) {
        this.listener = listener;
    }

    public ClubsAdapter.ClubsHolder onCreateViewHolder(ViewGroup parent, int type) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.items_clubs, parent, false);

        final ClubsHolder holder = new ClubsHolder(view);
        view.setOnClickListener(v -> listener.onItemClick(view, holder.getAdapterPosition()));

        return holder;
    }


    // Set the values of each club
    public void onBindViewHolder(ClubsAdapter.ClubsHolder holder, int position) {
        ClubEntity item = data.get(position);

        // Set the logo of each club
        String picture = item.getLogo();
        picture = picture.substring(0, picture.indexOf("."));
        int id = holder.itemView.getResources().getIdentifier(picture, "drawable", holder.itemView.getContext().getPackageName());
        holder.clubLogo.setImageResource(id);

        // Set the correct data to each card in the view
        holder.clubName.setText(item.getName());

        // Set the favorite star of each club
        boolean favorite = item.getFavorite();

        if (favorite) {
            holder.clubFavorite.setImageResource(R.drawable.ic_favorite);
        } else {
            holder.clubFavorite.setImageResource(R.drawable.ic_notfavorite);
        }
    }


    // Number of cards to show (related to the number of clubs)
    public int getItemCount() {
        if (data != null) {
            return data.size();
        } else {
            return 0;
        }
    }


    // Set the data
    public void setData(final List<ClubEntity> clubs) {
        if (data == null) {
            data = clubs;
            notifyItemRangeInserted(0, clubs.size());
        } else {
            DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {

                // Overrided methods
                public int getOldListSize() {
                    return data.size();
                }

                public int getNewListSize() {
                    return clubs.size();
                }

                public boolean areItemsTheSame(int oldPos, int newPos) {
                    return data.get(oldPos).getId().equals(clubs.get(newPos).getId());
                }

                public boolean areContentsTheSame(int oldPos, int newPos) {
                    ClubEntity newClub = clubs.get(newPos);
                    ClubEntity oldClub = data.get(newPos);

                    return newClub.getId().equals(oldClub.getId())
                            && Objects.equals(newClub.getName(), oldClub.getName());
                }
            });

            data = clubs;
            result.dispatchUpdatesTo(this);
        }
    }
}
