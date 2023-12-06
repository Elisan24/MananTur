package com.example.manantur2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainAdapter2 extends FirebaseRecyclerAdapter<MainModel2, MainAdapter2.myViewHolder2 > {

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public MainAdapter2(@NonNull FirebaseRecyclerOptions<MainModel2> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder2 holder, int position, @NonNull MainModel2 model) {
        holder.Evento.setText(model.getEvento());
        holder.Lugar.setText(model.getLugar());
        holder.Fecha.setText(model.getFecha());
        holder.Descripcion.setText(model.getDescripcion());
        holder.Organizador.setText(model.getOrganizador());

        Glide.with(holder.img.getContext())
                .load(model.getFoto())
                .placeholder(com.firebase.ui.database.R.drawable.common_google_signin_btn_icon_dark)
                .circleCrop()
                .error(com.firebase.ui.database.R.drawable.common_google_signin_btn_icon_dark_normal)
                .into(holder.img);
    }
    @NonNull
    @Override
    public myViewHolder2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.main_item_2, parent, false);
        return new myViewHolder2(view);
    }

    class myViewHolder2 extends RecyclerView.ViewHolder{
        CircleImageView img;
        TextView Evento, Lugar, Fecha, Descripcion, Organizador;

        public myViewHolder2(@NonNull View itemView) {
            super(itemView);

            img =(CircleImageView) itemView.findViewById(R.id.img1);
            Evento=(TextView)itemView.findViewById(R.id.evento);
            Lugar=(TextView) itemView.findViewById(R.id.lugar);
            Fecha=(TextView) itemView.findViewById(R.id.TextFecha);
            Descripcion=(TextView) itemView.findViewById(R.id.descripcion);
            Organizador=(TextView) itemView.findViewById(R.id.organizador);
        }
    }



}
