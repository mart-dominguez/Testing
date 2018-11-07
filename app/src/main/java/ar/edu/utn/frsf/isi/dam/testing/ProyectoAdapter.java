package ar.edu.utn.frsf.isi.dam.testing;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import ar.edu.utn.frsf.isi.dam.testing.modelo.Proyecto;

public class ProyectoAdapter extends RecyclerView.Adapter<ProyectoAdapter.ViewHolder> {
        private List<Proyecto> mDataset;

        // Provide a reference to the views for each data item
        // Complex data items may need more than one view per item, and
        // you provide access to all the views for a data item in a view holder
        public static class ViewHolder extends RecyclerView.ViewHolder {
            // each data item is just a string in this case
            public TextView tvNombre;
            public TextView tvPresupuesto;
            public TextView tvHoras;
            public ImageView imgCliente;
            public Button btnEditar;
            public ViewHolder(View v) {
                super(v);
                tvNombre = (TextView) v.findViewById(R.id.pry_nombre);
                tvPresupuesto = (TextView) v.findViewById(R.id.pry_presup);
                tvHoras = (TextView) v.findViewById(R.id.pry_horas);
                imgCliente = (ImageView) v.findViewById(R.id.cliente_img);
                btnEditar = (Button) v.findViewById(R.id.btn_editar);
            }
        }

        // Provide a suitable constructor (depends on the kind of dataset)
        public ProyectoAdapter(List<Proyecto> myDataset) {
            mDataset = myDataset;
        }

        // Create new views (invoked by the layout manager)
        @Override
        public ProyectoAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                         int viewType) {
            // create a new view
            LinearLayout v = (LinearLayout) LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.card_proyecto, parent, false);
            ViewHolder vh = new ViewHolder(v);
            return vh;
        }

        // Replace the contents of a view (invoked by the layout manager)
        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            // - get element from your dataset at this position
            // - replace the contents of the view with that element
            //holder.mTextView.setText(mDataset[position]);
            holder.tvNombre.setText(mDataset.get(position).getNombre());
            holder.tvHoras.setText(mDataset.get(position).getHoras()+" Hs.");
            holder.tvPresupuesto.setText("$ "+mDataset.get(position).getPresupuesto());
            holder.imgCliente.setImageResource(R.drawable.cliente_48);
            holder.btnEditar.setTag(mDataset.get(position).getId());
            holder.btnEditar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int id = Integer.valueOf(view.getTag().toString()).intValue();
                    Bundle b = new Bundle();
                    Intent i1 = new Intent(view.getContext(),ABMProyectoActivity.class);
                    i1.putExtra("id_proyecto",id);
                    view.getContext().startActivity(i1);
                }
            });
        }

        // Return the size of your dataset (invoked by the layout manager)
        @Override
        public int getItemCount() {
            return mDataset.size();
        }
    }

