package org.firstinspires.ftc.teamcode;

import com.acmerobotics.roadrunner.geometry.Pose2d;

import org.firstinspires.ftc.robotcore.external.matrices.VectorF;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Quaternion;
import org.firstinspires.ftc.teamcode.FieldReferenceLibrary;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagMetadata;

import java.util.ArrayList;

public class FieldReferenceLibrary {

    public FieldReference[] references;

    public FieldReferenceLibrary(FieldReference[] references) {
        this.references = references;
    }

    private FieldReference getTagByID(int id) {
        for (FieldReference ref : references) {
            if (ref.tag_id == id) {
                return ref;
            }
        }
        return references[0];
    }

    public Pose2d locate ( AprilTagDetection pic )
    {
        return getTagByID(pic.id).locate( pic ) ;
    }

    public static class Builder
    {
        private ArrayList<FieldReference> data = new ArrayList<>();

        public FieldReferenceLibrary.Builder addTag(int id, float posX, float posY, int angle ) {
            data.add(new FieldReference(id, posX, posY, angle));
            return this;
        }
        /**
         * Create an {@link FieldReferenceLibrary} object from the specified tags
         * @return an {@link FieldReferenceLibrary} object
         */
        public FieldReferenceLibrary build()
        {
            return new FieldReferenceLibrary(data.toArray(new FieldReference[0]));
        }
    }
}