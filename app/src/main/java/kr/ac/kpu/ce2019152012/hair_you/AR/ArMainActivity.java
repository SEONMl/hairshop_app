package kr.ac.kpu.ce2019152012.hair_you.AR;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentOnAttachListener;

import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.Toast;

import com.google.ar.core.Anchor;
import com.google.ar.core.Config;
import com.google.ar.core.HitResult;
import com.google.ar.core.Plane;
import com.google.ar.core.Session;
import com.google.ar.sceneform.AnchorNode;
import com.google.ar.sceneform.ArSceneView;
import com.google.ar.sceneform.Node;
import com.google.ar.sceneform.SceneView;
import com.google.ar.sceneform.Sceneform;
import com.google.ar.sceneform.math.Vector3;
import com.google.ar.sceneform.rendering.ModelRenderable;
import com.google.ar.sceneform.rendering.Renderable;
import com.google.ar.sceneform.rendering.ViewRenderable;
import com.google.ar.sceneform.ux.ArFragment;
import com.google.ar.sceneform.ux.BaseArFragment;
import com.google.ar.sceneform.ux.TransformableNode;


import java.lang.ref.WeakReference;

import kr.ac.kpu.ce2019152012.hair_you.R;


public class ArMainActivity extends AppCompatActivity implements
        FragmentOnAttachListener,
        BaseArFragment.OnTapArPlaneListener,
        BaseArFragment.OnSessionConfigurationListener,
        ArFragment.OnViewCreatedListener {

    private ArFragment arFragment;
    private ViewRenderable viewRenderable;
    private Renderable model;

    @Override
    @SuppressWarnings({"AndroidAppChecker", "FutureReturnValueIgnored"})
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ar_main);
        Log.i("TAG", "ArMainActivity 실행됨");

        arFragment = (ArFragment) getSupportFragmentManager().findFragmentById(R.id.arFragment);
        getSupportFragmentManager().addFragmentOnAttachListener(this);
        if(savedInstanceState == null){
            if(Sceneform.isSupported(this)){
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.arFragment, ArFragment.class, null)
                        .commit();
            }
            Log.i("TAG", "onCreate: loadModels() 실행 전");
            loadModels();
        }

    }

    @Override
    public void onAttachFragment(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment) {
        if (fragment.getId() == R.id.arFragment){
            arFragment =(ArFragment) fragment;
            arFragment.setOnSessionConfigurationListener(this);
            arFragment.setOnViewCreatedListener(this);
            arFragment.setOnTapArPlaneListener(this);
        }
    }

    @Override
    public void onSessionConfiguration(Session session, Config config) {
        if(session.isDepthModeSupported(Config.DepthMode.AUTOMATIC)){
            config.setDepthMode(Config.DepthMode.AUTOMATIC);
        }
    }

    @Override
    public void onViewCreated(ArSceneView arSceneView) {
        arFragment.setOnViewCreatedListener(null);

        // Fine adjust the maximum frame rate
        arSceneView.setFrameRateFactor(SceneView.FrameRate.FULL);
    }


    public void loadModels() {
        Log.i("TAG", "loadModels: loadModels 실행됨");
        WeakReference<ArMainActivity> weakActivity = new WeakReference<ArMainActivity>( this);
        Log.i("TAG", "loadModels: weakReference 실행됨");

        ModelRenderable.builder()
                .setSource(this, Uri.parse("https://storage.googleapis.com/ar-answers-in-search-models/static/Tiger/model.glb"))
                .setIsFilamentGltf(true)
                .setAsyncLoadEnabled(true)
                .build()
                .thenAccept(model -> {
                    ArMainActivity activity = weakActivity.get();
                    if (activity != null){
                        activity.model =model;
                        Log.i("TAG", "loadModels: modelRederable 빌더 성공");
                    }
                })
                .exceptionally(throwable -> {
                    Toast.makeText(
                            this, "Unable to load model", Toast.LENGTH_LONG).show();
                    return null;
                });
        Log.i("TAG", "loadModels: Model Renderable실행됨");

        ViewRenderable.builder()
                .setView(this, R.layout.view_model_title)
                .build()
                .thenAccept(viewRenderable1 -> {
                    ArMainActivity activity = weakActivity.get();
                    if (activity!= null){
                        activity.viewRenderable = viewRenderable1;
                        Log.i("TAG", "loadModels: ViewRederable 빌더 성공");

                    }
                })
                .exceptionally(throwable -> {
                    Toast.makeText(this, "Unable to load model", Toast.LENGTH_LONG).show();
                    return null;
                });

    }

    @Override
    public void onTapPlane(HitResult hitResult, Plane plane, MotionEvent motionEvent) {
        if (model == null || viewRenderable == null) {
            Toast.makeText(this, "Loading...", Toast.LENGTH_LONG).show();
            return ;
        }

        // Create the Anchor
        Anchor anchor = hitResult.createAnchor();
        AnchorNode anchorNode = new AnchorNode(anchor);
        anchorNode.setParent(arFragment.getArSceneView().getScene());

        // Create the transformable model and add it to the anchor.
        TransformableNode model =new TransformableNode(arFragment.getTransformationSystem());
        model.setParent(anchorNode);
        model.setRenderable(this.model)
                .animate(true).start();
        model.select();

        Node titleNode = new Node();
        titleNode.setParent(model);
        titleNode.setEnabled(false);
        titleNode.setLocalPosition(new Vector3(0.0f, 1.0f, 0.0f));
        titleNode.setRenderable(viewRenderable);
        titleNode.setEnabled(true);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}