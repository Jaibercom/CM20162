package co.edu.udea.computacionmovil.materialdesign.model;


/**
 * Created by joluditru on 11/08/2016.
 */
public class Person {
    private String name;
    private String age;
    private int photoId;

    public Person(String name, String age, int photoId) {
        this.name = name;
        this.age = age;
        this.photoId = photoId;
    }

    public String getName() {
        return name;
    }

    public String getAge() {
        return age;
    }

    public int getPhotoId() {
        return photoId;
    }
}
