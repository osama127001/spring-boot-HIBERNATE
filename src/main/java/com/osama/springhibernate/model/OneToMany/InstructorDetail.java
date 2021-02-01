package com.osama.springhibernate.model.OneToMany;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "instructor_detail")
public class InstructorDetail implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "youtube_channel")
    private String youtubeChannel;

    @Column(name = "hobby")
    private String hobby;

    /*
    * 1. By adding the below property, we are making the relationship b/w instructor and instructor_detail
    *    bidirectional.
    * 2. mappedBy tells the Instructor class that look for 'instructorDetail' property.
    * 3. We need to make no changes in the DB to make the relationship bidirectional.
    * 4. If we want to delete instructor_detail and keep instructor than set below
    *    cascade = {CascadeType.DETACH, CascadeType.REFRESH, CascadeType.MERGE, CascadeType.PERSIST}, remove
    *    CascadeType.DELETE, also in the DAO class before deleting, break the association by using
    *    tempInstructorDetail.getInstructor().setInstructorDetail(null);
    */
    @OneToOne(mappedBy = "instructorDetail", cascade = CascadeType.ALL)
    private Instructor instructor;

    public InstructorDetail() {}

    public InstructorDetail(String youtubeChannel, String hobby) {
        this.youtubeChannel = youtubeChannel;
        this.hobby = hobby;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    public int getId() {
        return id;
    }

    public String getYoutubeChannel() {
        return youtubeChannel;
    }

    public String getHobby() {
        return hobby;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setYoutubeChannel(String youtubeChannel) {
        this.youtubeChannel = youtubeChannel;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    @Override
    public String toString() {
        return "InstructorDetail{" +
                "id=" + id +
                ", youtubeChannel='" + youtubeChannel + '\'' +
                ", hobby='" + hobby + '\'' +
                '}';
    }
}

