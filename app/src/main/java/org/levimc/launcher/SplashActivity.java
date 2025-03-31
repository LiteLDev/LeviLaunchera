package org.levimc.launcher;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.animation.AlphaAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

@SuppressLint("CustomSplashScreen")
public class SplashActivity extends AppCompatActivity {

    ImageView imgLeaf;
    TextView tvAppName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        setupSystemUI();

        imgLeaf = findViewById(R.id.imgLeaf);
        tvAppName = findViewById(R.id.tvAppName);
        applyTextGradient(tvAppName);

        startLeafAnimation();
        startAppNameAnimation();

        tvAppName.postDelayed(() -> {
            Intent newIntent = new Intent(this,MainActivity.class);
            startActivity(newIntent);
            finish();
        }, 2000);
    }

    private void setupSystemUI() {
        Window window = getWindow();
        window.setNavigationBarColor(Color.TRANSPARENT);
        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
    }

    private void startLeafAnimation() {
        TranslateAnimation translateAnimation = new TranslateAnimation(0, 0, -600, 0);
        translateAnimation.setDuration(1500);
        translateAnimation.setFillAfter(true);
        translateAnimation.setInterpolator(this, android.R.anim.bounce_interpolator);

        imgLeaf.startAnimation(translateAnimation);
    }

    private void applyTextGradient(TextView textView) {
        int startColor = Color.parseColor("#7BAAF7");
        int endColor = Color.parseColor("#B287F7");
        Shader shader = new LinearGradient(
                0, 0, textView.getPaint().measureText(textView.getText().toString()), 0,
                startColor, endColor, Shader.TileMode.CLAMP
        );
        textView.getPaint().setShader(shader);
    }

    private void startAppNameAnimation() {
        AlphaAnimation alphaAnimation = new AlphaAnimation(0f,1f);
        alphaAnimation.setDuration(2000);
        alphaAnimation.setFillAfter(true);
        tvAppName.startAnimation(alphaAnimation);
    }
}