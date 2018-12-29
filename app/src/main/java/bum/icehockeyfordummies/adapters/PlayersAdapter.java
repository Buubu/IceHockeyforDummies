package bum.icehockeyfordummies.adapters;

import android.graphics.Color;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;
import java.util.Objects;
import bum.icehockeyfordummies.R;
import bum.icehockeyfordummies.database.PlayerEntity;


public class PlayersAdapter extends RecyclerView.Adapter<PlayersAdapter.PlayersHolder> {
    private List<PlayerEntity> data;
    private ItemClickListener listener;


    // Class PlayersHolder
    public class PlayersHolder extends RecyclerView.ViewHolder {
        private TextView playerNumber;
        private TextView playerName;
        private View row;

        public PlayersHolder(View itemView) {
            super(itemView);

            playerNumber = itemView.findViewById(R.id.clubpage_pnumber);
            playerName = itemView.findViewById(R.id.clubpage_pname);
            row = itemView.findViewById(R.id.row);
        }
    }


    // Define the adapter
    public PlayersAdapter(ItemClickListener listener) {
        this.listener = listener;
    }

    public PlayersAdapter.PlayersHolder onCreateViewHolder(ViewGroup parent, int type) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.items_players, parent, false);

        final PlayersHolder holder = new PlayersHolder(view);
        view.setOnClickListener(v -> listener.onItemClick(view, holder.getAdapterPosition()));

        return holder;
    }


    // Set the values of each player
    public void onBindViewHolder(PlayersAdapter.PlayersHolder holder, int position) {
        PlayerEntity item = data.get(position);

        // Set the number of each player
        String number = "#" + item.getNumber();
        holder.playerNumber.setText(number);

        // Set the name of each player
        String name = item.toString();
        holder.playerName.setText(name);

        // Set the color of the row
        if (position%2 == 1) {
            holder.row.setBackgroundColor(Color.WHITE);
        }
    }


    // Number of lines to show (related to the number of players)
    public int getItemCount() {
        if (data != null) {
            return data.size();
        } else {
            return 0;
        }
    }


    // Set the data
    public void setData(final List<PlayerEntity> players) {
        if (data == null) {
            data = players;
            notifyItemRangeInserted(0, players.size());
        } else {
            DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {

                // Overrided methods
                public int getOldListSize() {
                    return data.size();
                }

                public int getNewListSize() {
                    return players.size();
                }

                public boolean areItemsTheSame(int oldPos, int newPos) {
                    return data.get(oldPos).getId().equals(players.get(newPos).getId());
                }

                public boolean areContentsTheSame(int oldPos, int newPos) {
                    PlayerEntity newPlayer = players.get(newPos);
                    PlayerEntity oldPlayer = data.get(newPos);

                    return newPlayer.getId().equals(oldPlayer.getId())
                            && Objects.equals(newPlayer.toString(), oldPlayer.toString());
                }
            });

            data = players;
            result.dispatchUpdatesTo(this);
        }
    }
}
