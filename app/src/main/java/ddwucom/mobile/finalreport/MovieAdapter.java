package ddwucom.mobile.finalreport;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MovieAdapter extends BaseAdapter {
    Context context;
    int layout;
    ArrayList<Movie> movieList;
    LayoutInflater layoutInflater;

    public MovieAdapter(Context context, int layout, ArrayList<Movie> movieList) {
        this.context = context;
        this.layout = layout;
        this.movieList = movieList;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return movieList.size();
    }

    @Override
    public Object getItem(int pos) {
        return movieList.get(pos);
    }

    @Override
    public long getItemId(int pos) {
        return movieList.get(pos).get_id();
    }

    @Override
    public View getView(int pos, View view, ViewGroup viewGroup) {
        final int position = pos;

        if (view == null) {
            view = layoutInflater.inflate(R.layout.activity_custom, viewGroup, false);
        }

        TextView tvTitle = (TextView) view.findViewById(R.id.tvTitle);
        TextView tvActor = (TextView) view.findViewById(R.id.tvActor);
        TextView tvGenre = (TextView) view.findViewById(R.id.tvGenre);
        TextView tvDirector = (TextView) view.findViewById(R.id.tvDirector);
        TextView tvDate = (TextView) view.findViewById(R.id.tvDate);
//        ImageView ivPoster = (ImageView) view.findViewById(R.id.ivPoster);
//
//
//        byte[] image = movieList.get(position).getImage();
//        Bitmap bitmap = BitmapFactory.decodeByteArray(image, 0, image.length);
//        ivPoster.setImageBitmap(bitmap);

        tvTitle.setText(movieList.get(position).getTitle());
        tvActor.setText(movieList.get(position).getActor());
        tvGenre.setText(movieList.get(position).getGenre());
        tvDirector.setText(movieList.get(position).getDirector());
        tvDate.setText(movieList.get(position).getDate());

        return view;
    }
}
