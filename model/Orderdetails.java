/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author chook
 */
@Entity
@Table(name = "ORDERDETAILS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Orderdetails.findAll", query = "SELECT o FROM Orderdetails o"),
    @NamedQuery(name = "Orderdetails.findByProductid", query = "SELECT o FROM Orderdetails o WHERE o.orderdetailsPK.productid = :productid"),
    @NamedQuery(name = "Orderdetails.findByOrderid", query = "SELECT o FROM Orderdetails o WHERE o.orderdetailsPK.orderid = :orderid"),
    @NamedQuery(name = "Orderdetails.findByDatefororder", query = "SELECT o FROM Orderdetails o WHERE o.datefororder = :datefororder"),
    @NamedQuery(name = "Orderdetails.findByQuantity", query = "SELECT o FROM Orderdetails o WHERE o.quantity = :quantity"),
    @NamedQuery(name = "Orderdetails.findByPrice", query = "SELECT o FROM Orderdetails o WHERE o.price = :price")})
public class Orderdetails implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected OrderdetailsPK orderdetailsPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DATEFORORDER")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datefororder;
    @Basic(optional = false)
    @NotNull
    @Column(name = "QUANTITY")
    private int quantity;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "PRICE")
    private String price;
    @JoinColumn(name = "ORDERID", referencedColumnName = "ORDERID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Ordertable ordertable;
    @JoinColumn(name = "PRODUCTID", referencedColumnName = "PRODUCTID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Product product;

    public Orderdetails() {
    }

    public Orderdetails(OrderdetailsPK orderdetailsPK) {
        this.orderdetailsPK = orderdetailsPK;
    }

    public Orderdetails(OrderdetailsPK orderdetailsPK, Date datefororder, int quantity, String price) {
        this.orderdetailsPK = orderdetailsPK;
        this.datefororder = datefororder;
        this.quantity = quantity;
        this.price = price;
    }

    public Orderdetails(String productid, String orderid) {
        this.orderdetailsPK = new OrderdetailsPK(productid, orderid);
    }

    public OrderdetailsPK getOrderdetailsPK() {
        return orderdetailsPK;
    }

    public void setOrderdetailsPK(OrderdetailsPK orderdetailsPK) {
        this.orderdetailsPK = orderdetailsPK;
    }

    public Date getDatefororder() {
        return datefororder;
    }

    public void setDatefororder(Date datefororder) {
        this.datefororder = datefororder;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Ordertable getOrdertable() {
        return ordertable;
    }

    public void setOrdertable(Ordertable ordertable) {
        this.ordertable = ordertable;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (orderdetailsPK != null ? orderdetailsPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Orderdetails)) {
            return false;
        }
        Orderdetails other = (Orderdetails) object;
        if ((this.orderdetailsPK == null && other.orderdetailsPK != null) || (this.orderdetailsPK != null && !this.orderdetailsPK.equals(other.orderdetailsPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Orderdetails[ orderdetailsPK=" + orderdetailsPK + " ]";
    }
    
}
