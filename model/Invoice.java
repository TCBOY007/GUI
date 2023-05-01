/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
@Table(name = "INVOICE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Invoice.findAll", query = "SELECT i FROM Invoice i"),
    @NamedQuery(name = "Invoice.findByInvoiceid", query = "SELECT i FROM Invoice i WHERE i.invoiceid = :invoiceid"),
    @NamedQuery(name = "Invoice.findByInvoicedate", query = "SELECT i FROM Invoice i WHERE i.invoicedate = :invoicedate"),
    @NamedQuery(name = "Invoice.findByTotalamount", query = "SELECT i FROM Invoice i WHERE i.totalamount = :totalamount")})
public class Invoice implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "INVOICEID")
    private String invoiceid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "INVOICEDATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date invoicedate;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "TOTALAMOUNT")
    private String totalamount;
    @JoinColumn(name = "CUSTOMERID", referencedColumnName = "CUSTOMERID")
    @ManyToOne(optional = false)
    private Customer customerid;
    @JoinColumn(name = "PAYMENTID", referencedColumnName = "PAYMENTID")
    @ManyToOne(optional = false)
    private Payment paymentid;

    public Invoice() {
    }

    public Invoice(String invoiceid) {
        this.invoiceid = invoiceid;
    }

    public Invoice(String invoiceid, Date invoicedate, String totalamount) {
        this.invoiceid = invoiceid;
        this.invoicedate = invoicedate;
        this.totalamount = totalamount;
    }

    public String getInvoiceid() {
        return invoiceid;
    }

    public void setInvoiceid(String invoiceid) {
        this.invoiceid = invoiceid;
    }

    public Date getInvoicedate() {
        return invoicedate;
    }

    public void setInvoicedate(Date invoicedate) {
        this.invoicedate = invoicedate;
    }

    public String getTotalamount() {
        return totalamount;
    }

    public void setTotalamount(String totalamount) {
        this.totalamount = totalamount;
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
        hash += (invoiceid != null ? invoiceid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Invoice)) {
            return false;
        }
        Invoice other = (Invoice) object;
        if ((this.invoiceid == null && other.invoiceid != null) || (this.invoiceid != null && !this.invoiceid.equals(other.invoiceid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Invoice[ invoiceid=" + invoiceid + " ]";
    }
    
}
