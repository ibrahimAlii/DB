package ibrahim.db;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import java.util.Objects;

import ibrahim.db.databinding.ActivityDetailsBinding;
import ibrahim.db.utils.InjectorUtils;
import ibrahim.db.viewmodel.AdDetailsViewModel;

public class DetailsActivity extends AppCompatActivity {

    public static final String AD_ID = "ad_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AdDetailsViewModel adDetailsViewModel = new ViewModelProvider(this,
                InjectorUtils.INSTANCE.provideAdDetailsViewModel(this)).get(AdDetailsViewModel.class);
        super.onCreate(savedInstanceState);

        ActivityDetailsBinding view = DataBindingUtil.setContentView(this, R.layout.activity_details);

        setSupportActionBar(view.toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);

        String adId = getIntent().getStringExtra(AD_ID);
        adDetailsViewModel.getAdById(adId).observe(this, view::setAd);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}