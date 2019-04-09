package nl.saxion.touristattractiontagger.CompoundControls;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import nl.saxion.touristattractiontagger.R;

public class AttractionDisplayCompound extends LinearLayout {
    private TextView tvName;
    private TextView tvType;
    private TextView tvSpecialAttribute;
    private CheckBox cbAddVisitedPlaces;

    public AttractionDisplayCompound(Context context) {
        super(context);
        init();
    }

    public AttractionDisplayCompound(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public AttractionDisplayCompound(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public AttractionDisplayCompound(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init(){
        LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.custom_attraction_display, this);

        this.tvName = findViewById(R.id.tvAttractionName);
        this.tvType = findViewById(R.id.tvAttractionType);
        this.tvSpecialAttribute= findViewById(R.id.tvSpecialAttribute);
        this.cbAddVisitedPlaces= findViewById(R.id.cbAdd);
    }

    public TextView getTvName() {
        return tvName;
    }

    public TextView getTvType() {
        return tvType;
    }

    public TextView getTvSpecialAttribute() {
        return tvSpecialAttribute;
    }

    public CheckBox getCbAddVisitedPlaces() {
        return cbAddVisitedPlaces;
    }
}
