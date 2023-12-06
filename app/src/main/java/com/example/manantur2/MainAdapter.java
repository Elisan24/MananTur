package com.example.manantur2;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;


public class MainAdapter extends FirebaseRecyclerAdapter<MainModel, MainAdapter.myViewHolder> {


 /**
  * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
  * {@link FirebaseRecyclerOptions} for configuration options.
  *
  * @param options
  */
 public MainAdapter(@NonNull FirebaseRecyclerOptions<MainModel> options) {

  super(options);
 }

 @Override
 protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull MainModel model) {
  holder.Nombre.setText(model.getNombre());
  holder.ApellidoPaterno.setText(model.getApellidoPaterno());
  holder.ApellidoMaterno.setText(model.getApellidoMaterno());
  holder.Email.setText(model.getEmail());
  holder.Password.setText(model.getPassword());




 }

 @NonNull
 @Override
 public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
  View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.main_item, parent, false);
  return new myViewHolder(view);
 }

  static class myViewHolder extends RecyclerView.ViewHolder{
  EditText Nombre, ApellidoPaterno, ApellidoMaterno, Email, Password;

  public myViewHolder(@NonNull View itemView) {
   super(itemView);
   Nombre=(EditText) itemView.findViewById(R.id.editTextText8);
   ApellidoPaterno=(EditText) itemView.findViewById(R.id.editTextText9);
   ApellidoMaterno=(EditText) itemView.findViewById(R.id.editTextText10);
   Email=(EditText) itemView.findViewById(R.id.editTextText11);
   Password=(EditText) itemView.findViewById(R.id.editTextText12);
  }


 }



// Aquí puedes descomentar y completar el código para myViewHolder2 si es necesario
     /*static class myViewHolder2 extends RecyclerView.ViewHolder {
        CircleImageView img;
        TextView Evento, Lugar, Fecha, Descripcion, Organizador;

        public myViewHolder2(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img1);
            Evento = itemView.findViewById(R.id.evento);
            Lugar = itemView.findViewById(R.id.lugar);
            Fecha = itemView.findViewById(R.id.TextFecha);
            Descripcion = itemView.findViewById(R.id.descripcion);
            Organizador = itemView.findViewById(R.id.organizador);
        }
    }
*/

}





