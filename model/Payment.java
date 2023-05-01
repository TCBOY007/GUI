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
@Table(name = "PAYMENT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Payment.findAll", query = "SELECT p FROM Payment p"),
    @NamedQuery(name = "Payment.findByPaymentid", query = "SELECT p FROM Payment p WHERE p.paymentid = :paymentid"),
    @NamedQuery(name = "Payment.findByPaymentdate", query = "SELECT p FROM Payment p WHERE p.paymentdate = :paymentdate"),
    @NamedQuery(name = "Payment.findByPaymentamount", query = "SELECT p FROM Payment p WHERE p.paymentamount = :paymentamount"),
    @NamedQuery(name = "Payment.findByPaymentmethod", query = "SELECT p FROM Payment p WHERE p.paymentmethod = :paymentmethod"),
    @NamedQuery(name = "Payment.findByPaymentstatus", query = "SELECT p FROM Payment p WHERE p.paymentstatus = :paymentstatus")})
public class Payment implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "PAYMENTDATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date paymentdate;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "PAYMENTAMOUNT")
    private String paymentamount;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "PAYMENTMETHOD")
    private String paymentmethod;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "PAYMENTSTATUS")
    private String paymentstatus;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "PAYMENTID")
    private String paymentid;
    @JoinColumn(name = "CUSTOMERID", referencedColumnName = "CUSTOMERID")
    @ManyToOne(optional = false)
    private Customer customerid;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "paymentid")
    private List<Invoice> invoiceList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "paymentid")
    private List<Ordertable> ordertableList;

    public Payment() {
    }

    public Payment(String paymentid) {
        this.paymentid = paymentid;
    }

    public Payment(String paymentid, Date paymentdate, String paymentamount, String paymentmethod, String paymentstatus) {
        this.paymentid = paymentid;
        this.paymentdate = paymentdate;
        this.paymentamount = paymentamount;
        this.paymentmethod = paymentmethod;
        this.paymentstatus = paymentstatus;
    }

    public String getPaymentid() {
        return paymentid;
    }

    public void setPaymentid(String paymentid) {
        this.paymentid = paymentid;
    }


    public Customer getCustomerid() {
        return customerid;
    }

    public void setCustomerid(Customer customerid) {
        this.customerid = customerid;
    }

    @XmlTransient
    public List<Invoice> getInvoiceList() {
        return invoiceList;
    }

    public void setInvoiceList(List<Invoice> invoiceList) {
        this.invoiceList = invoiceList;
    }

    @XmlTransient
    public List<Ordertable> getOrdertableList() {
        return ordertableList;
    }

    public void setOrdertableList(List<Ordertable> ordertableList) {
        this.ordertableList = ordertableList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (paymentid != null ? paymentid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Payment)) {
            return false;
        }
        Payment other = (Payment) object;
        if ((this.paymentid == null && other.paymentid != null) || (this.paymentid != null && !this.paymentid.equals(other.paymentid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Payment[ paymentid=" + paymentid + " ]";
    }

    public Date getPaymentdate() {
        return paymentdate;
    }

    public void setPaymentdate(Date paymentdate) {
        this.paymentdate = paymentdate;
    }

    public String getPaymentamount() {
        return paymentamount;
    }

    public void setPaymentamount(String paymentamount) {
        this.paymentamount = paymentamount;
    }

    public String getPaymentmethod() {
        return paymentmethod;
    }

    public void setPaymentmethod(String paymentmethod) {
        this.paymentmethod = paymentmethod;
    }

    public String getPaymentstatus() {
        return paymentstatus;
    }

    public void setPaymentstatus(String paymentstatus) {
        this.paymentstatus = paymentstatus;
    }
    
}
