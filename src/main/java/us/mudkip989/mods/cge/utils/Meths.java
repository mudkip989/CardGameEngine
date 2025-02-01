package us.mudkip989.mods.cge.utils;

import org.bukkit.*;
import org.bukkit.util.*;
import org.joml.*;

public class Meths {

    /**
     * Rotates vectors around the quaternion.
     * @param vecs - Transformation Vectors.
     * @param quad - Quaternion describing rotation.
     * @return new Array
     */
    public static Vector3d[] rotateVectors(Vector3d[] vecs, Quaterniond quad){
        Vector3d[] newarr = new Vector3d[3];
        int i = 0;
        for(Vector3d vec: vecs){
            Quaterniond vecq = new Quaterniond(vec.x, vec.y, vec.z, 0);
            Quaterniond iquad = new Quaterniond(quad.x * -1,quad.y * -1,quad.z * -1,quad.w);
            Quaterniond result = multiply(multiply(quad, vecq), iquad);
            newarr[i] = new Vector3d(result.x, result.y, result.z);
            i++;
        }


        return newarr;
    }

    public static Quaterniond multiply(Quaterniond quada, Quaterniond quadb){
        double cx = quada.x;
        double cy = quada.y;
        double cz = quada.z;
        double cw = quada.w;
        double rx = quadb.x;
        double ry = quadb.y;
        double rz = quadb.z;
        double rw = quadb.w;

        double w = cw*rw - cx*rx - cy*ry - cz*rz;
        double x = cw*rx + cx*rw + cy*rz - cz*ry;
        double y = cw*ry - cx*rz + cy*rw + cz*rx;
        double z = cw*rz + cx*ry - cy*rx + cz*rw;

        return new Quaterniond(x, y, z, w);

    }

    public static Location shiftLocationForwards(Location loc, Float amount){
        Vector dir = loc.getDirection();
        dir.multiply(amount);
        loc.add(dir);

        return loc;
    }
}
