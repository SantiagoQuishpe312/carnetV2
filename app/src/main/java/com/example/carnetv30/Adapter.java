package com.example.carnetv30;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class Adapter extends BaseAdapter {
    private Context context;
    private List<Estudiante> datos;

    public Adapter(Context context, List<Estudiante> datos) {
        this.context = context;
        this.datos = datos;
    }

    @Override
    public int getCount() {
        return datos.size();
    }

    @Override
    public Object getItem(int position) {
        return datos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.activity_mostrar, parent, false);

            holder = new ViewHolder();
            holder.txtId = convertView.findViewById(R.id.txtId);
            holder.txtNombre = convertView.findViewById(R.id.txtNombre);
            holder.txtApellido = convertView.findViewById(R.id.txtApellido1);
            holder.txtCargo = convertView.findViewById(R.id.txtCargo);
            //holder.imgFoto = convertView.findViewById(R.id.imageView2);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Estudiante estudiante = datos.get(position);

        // Configura los datos en la vista
        holder.txtId.setText(String.valueOf(estudiante.getId()));
        holder.txtNombre.setText(estudiante.getNombre());
        holder.txtApellido.setText(estudiante.getApellido());
        holder.txtCargo.setText(estudiante.getCargo());
        // Asegúrate de configurar la imagen (imgFoto) según tus necesidades

        return convertView;
    }

    // Clase ViewHolder para mejorar el rendimiento de la lista
    private static class ViewHolder {
        TextView txtId;
        TextView txtNombre;
        TextView txtApellido;
        TextView txtCargo;
        ImageView imgFoto;
    }
}
