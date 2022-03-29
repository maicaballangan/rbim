/* Copyright (C) 2022 Jamaica Ballangan - All Rights Reserved
 * Clients may use and modify this code under the
 * terms and agreement only. Selling or distribution is prohibited
 * without the consent of the author
 */
package models;

import controllers.CRUD;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import play.db.jpa.GenericModel;

import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

/**
 * @author Maica Ballangan
 * @since v1
 */
@MappedSuperclass
public class AbstractModel extends GenericModel {

    @CRUD.Hidden
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    protected Date created;

    @CRUD.Hidden
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    protected Date updated;
}
