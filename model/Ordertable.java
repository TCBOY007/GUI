/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author chook
 */
@Entity
@Table(name = "ORDERTABLE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ordertable.findAll", query = "SELECT o FROM Ordertable o"),
    @NamedQuery(name = "Ordertable.findByOrderid", query = "SELECT o FROM Ordertable o WHERE o.orderid = :orderid"),
    @NamedQuery(name = "Ordertable.findByOrderdate", query = "SELECT o FROM Ordertable o WHERE o.orderdate = :orderdate"),
    @NamedQuery(name = "Ordertable.findByShippingaddress", query = "SELECT o FROM Ordertable o WHERE o.shippingaddress = :shippingaddress"),
    @NamedQuery(name = "Ordertable.findByGrandtotal", query = "SELECT o FROM Ordertable o WHERE o.grandtotal = :grandtotal"),
    @NamedQuery(name = "Ordertable.findByOrderstatus", query = "SELECT o FROM Ordertable o WHERE o.orderstatus = :orderstatus")})
public class Ordertable implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "ORDERDATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date orderdate;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "SHIPPINGADDRESS")
    private String shippingaddress;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "GRANDTOTAL")
    private String grandtotal;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "ORDERSTATUS")
    private String orderstatus;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ordertable")
    private List<Orderdetails> orderdetailsList;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "ORDERID")
    private String orderid;
    @JoinColumn(name = "CUSTOMERID", referencedColumnName = "CUSTOMERID")
    @ManyToOne(optional = false)
    private Customer customerid;
    @JoinColumn(name = "PAYMENTID", referencedColumnName = "PAYMENTID")
    @ManyToOne(optional = false)
    private Payment paymentid;

    public Ordertable() {
    }

    public Ordertable(String orderid) {
        this.orderid = orderid;
    }

    public Ordertable(String orderid, Date orderdate, String shippingaddress, String grandtotal, String orderstatus) {
        this.orderid = orderid;
        this.orderdate = orderdate;
        this.shippingaddress = shippingaddress;
        this.grandtotal = grandtotal;
        this.orderstatus = orderstatus;
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }


    public Customer getCustomerid() {
        return customerid;
    }

    public void setCustomerid(Customer customerid) {
        this.customerid = customerid;
    }

    public Payment getPaymentid() {
        return paymentid;
    }

    public void setPaymentid(Payment paymentid) {
        this.paymentid = paymentid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (orderid != null ? orderid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ordertable)) {
            return false;
        }
        Ordertable other = (Ordertable) object;
        if ((this.orderid == null && other.orderid != null) || (this.orderid != null && !this.orderid.equals(other.orderid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Ordertable[ orderid=" + orderid + " ]";
    }

    public Date getOrderdate() {
        return orderdate;
    }

    public void setOrderdate(Date orderdate) {
        this.orderdate = orderdate;
    }

    public String getShippingaddress() {
        return shippingaddress;
    }

    public void setShippingaddress(String shippingaddress) {
        this.shippingaddress = shippingaddress;
    }

    public String getGrandtotal() {
        return grandtotal;
    }

    public void setGrandtotal(String grandtotal) {
        this.grandtotal = grandtotal;
    }

    public String getOrderstatus() {
        return orderstatus;
    }

    public void setOrderstatus(String orderstatus) {
        this.orderstatus = orderstatus;
    }

    @XmlTransient
    public List<Orderdetails> getOrderdetailsList() {
        return orderdetailsList;
    }

    public void setOrderdetailsList(List<Orderdetails> orderdetailsList) {
        this.orderdetailsList = orderdetailsList;
    }
    
}
