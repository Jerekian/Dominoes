package ru.vsu.csf.proskuryakov.utils;

import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import ru.vsu.csf.proskuryakov.data.essence.Bone;
import ru.vsu.csf.proskuryakov.data.essence.BoneNumbers;

import java.awt.*;
import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

public class GUIUtils {

    public static final Image ZERO_HALF_BONE = new Image("/img/0.png");
    public static final Image ONE_HALF_BONE = new Image("/img/1.png");
    public static final Image TWO_HALF_BONE = new Image("/img/2.png");
    public static final Image THREE_HALF_BONE = new Image("/img/3.png");
    public static final Image FOUR_HALF_BONE = new Image("/img/4.png");
    public static final Image FIVE_HALF_BONE = new Image("/img/5.png");;
    public static final Image SIX_HALF_BONE = new Image("/img/6.png");
    private static final int BONE_SIZE = 30;

    public static List<Node> getDrawBoneList(List<Bone> playerBone){
        List<Node> nodeList = new ArrayList<>();

        for(Bone bone: playerBone){
            HBox hBox = new HBox();
            hBox.setSpacing(0);
            hBox.getChildren().clear();
            hBox.getChildren().addAll(
                    getHalfBoneImageView(bone.getPipsOnFirstHalf()),
                    getHalfBoneImageView(bone.getPipsOnSecondHalf())
            );
            nodeList.add(hBox);
        }
        return nodeList;
    }


    public static ImageView getHalfBoneImageView(BoneNumbers boneNumbers){
        ImageView imageView = new ImageView(getHalfBoneImage(boneNumbers));
        imageView.setFitWidth(BONE_SIZE);
        imageView.setPreserveRatio(true);
        imageView.setSmooth(true);

        return imageView;
    }

    public static Image getHalfBoneImage(BoneNumbers boneNumbers){
        switch (boneNumbers){
            case ZERO:
                return ZERO_HALF_BONE;
            case ONE:
                return ONE_HALF_BONE;
            case TWO:
                return TWO_HALF_BONE;
            case THREE:
                return THREE_HALF_BONE;
            case FOUR:
                return FOUR_HALF_BONE;
            case FIVE:
                return FIVE_HALF_BONE;
            case SIX:
                return SIX_HALF_BONE;
        }
        return null;
    }

}
