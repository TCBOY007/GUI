/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author chook
 */
@Entity
@Table(name = "ORDER_CART")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OrderCart.findAll", query = "SELECT o FROM OrderCart o"),
    @NamedQuery(name = "OrderCart.findByCartid", query = "SELECT o FROM OrderCart o WHERE o.cartid = :cartid"),
    @NamedQuery(name = "OrderCart.findByQuantity", query = "SELECT o FROM OrderCart o WHERE o.quantity = :quantity"),
    @NamedQuery(name = "OrderCart.findByPrice", query = "SELECT o FROM OrderCart o WHERE o.price = :price"),
    @NamedQuery(name = "OrderCart.findBySubtotal", query = "SELECT o FROM OrderCart o WHERE o.subtotal = :subtotal")})
public class OrderCart implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "CARTID")
    private String cartid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "QUANTITY")
    private int quantity;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "PRICE")
    private String price;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "SUBTOTAL")
    private String subtotal;
    @JoinColumn(name = "CUSTOMERID", referencedColumnName = "CUSTOMERID")
    @ManyToOne(optional = false)
    private Customer customerid;
    @JoinColumn(name = "PRODUCTID", referencedColumnName = "PRODUCTID")
    @ManyToOne(optional = false)
    private Product productid;

    public OrderCart() {
    }

    public OrderCart(String cartid) {
        this.cartid = cartid;
    }

    public OrderCart(String cartid, int quantity, String price, String subtotal) {
        this.cartid = cartid;
        this.quantity = quantity;
        this.price = price;
        this.subtotal = subtotal;
    }

    public String getCartid() {
        return cartid;
    }

    public void setCartid(String cartid) {
        this.cartid = cartid;
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

    public String getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(String subtotal) {
        this.subtotal = subtotal;
    }

    public Customer getCustomerid() {
        return customerid;
    }

    public void setCustomerid(Customer customerid) {
        this.customerid = customerid;
    }

    public Product getProductid() {
        return productid;
    }

    public void setProductid(Product productid) {
        this.productid = productid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cartid != null ? cartid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OrderCart)) {
            return false;
        }
        OrderCart other = (OrderCart) object;
        if ((this.cartid == null && other.cartid != null) || (this.cartid != null && !this.cartid.equals(other.cartid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.OrderCart[ cartid=" + cartid + " ]";
    }
    
}
