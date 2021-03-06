package com.generalstore.entity;
//// Generated 12-Apr-2022, 8:45:08 am by Hibernate Tools 5.6.3.Final
//
///**
// * OrderDetailId generated by hbm2java
// */
//public class OrderDetailId implements java.io.Serializable {
//
//	private Integer orderId;
//	private Integer bookId;
//	private int quantity;
//	private float subTotal;
//
//	public OrderDetailId() {
//	}
//
//	public OrderDetailId(int quantity, float subTotal) {
//		this.quantity = quantity;
//		this.subTotal = subTotal;
//	}
//
//	public OrderDetailId(Integer orderId, Integer bookId, int quantity, float subTotal) {
//		this.orderId = orderId;
//		this.bookId = bookId;
//		this.quantity = quantity;
//		this.subTotal = subTotal;
//	}
//
//	public Integer getOrderId() {
//		return this.orderId;
//	}
//
//	public void setOrderId(Integer orderId) {
//		this.orderId = orderId;
//	}
//
//	public Integer getBookId() {
//		return this.bookId;
//	}
//
//	public void setBookId(Integer bookId) {
//		this.bookId = bookId;
//	}
//
//	public int getQuantity() {
//		return this.quantity;
//	}
//
//	public void setQuantity(int quantity) {
//		this.quantity = quantity;
//	}
//
//	public float getSubTotal() {
//		return this.subTotal;
//	}
//
//	public void setSubTotal(float subTotal) {
//		this.subTotal = subTotal;
//	}
//
//	public boolean equals(Object other) {
//		if ((this == other))
//			return true;
//		if ((other == null))
//			return false;
//		if (!(other instanceof OrderDetailId))
//			return false;
//		OrderDetailId castOther = (OrderDetailId) other;
//
//		return ((this.getOrderId() == castOther.getOrderId()) || (this.getOrderId() != null
//				&& castOther.getOrderId() != null && this.getOrderId().equals(castOther.getOrderId())))
//				&& ((this.getBookId() == castOther.getBookId()) || (this.getBookId() != null
//						&& castOther.getBookId() != null && this.getBookId().equals(castOther.getBookId())))
//				&& (this.getQuantity() == castOther.getQuantity()) && (this.getSubTotal() == castOther.getSubTotal());
//	}
//
//	public int hashCode() {
//		int result = 17;
//
//		result = 37 * result + (getOrderId() == null ? 0 : this.getOrderId().hashCode());
//		result = 37 * result + (getBookId() == null ? 0 : this.getBookId().hashCode());
//		result = 37 * result + this.getQuantity();
//		result = 37 * result + (int) this.getSubTotal();
//		return result;
//	}
//
//}
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * OrderDetailId generated by hbm2java
 */
@Embeddable
public class OrderDetailId implements java.io.Serializable {

	private Book book;
	private BookOrder bookOrder;
	
	public OrderDetailId() {
	}


	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "book_id", insertable = false, updatable = false, nullable = false)
	public Book getBook() {
		return this.book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "order_id", insertable = false, updatable = false, nullable = false)
	public BookOrder getBookOrder() {
		return this.bookOrder;
	}

	public void setBookOrder(BookOrder bookOrder) {
		this.bookOrder = bookOrder;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((book == null) ? 0 : book.hashCode());
		result = prime * result + ((bookOrder == null) ? 0 : bookOrder.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderDetailId other = (OrderDetailId) obj;
		if (book == null) {
			if (other.book != null)
				return false;
		} else if (!book.equals(other.book))
			return false;
		if (bookOrder == null) {
			if (other.bookOrder != null)
				return false;
		} else if (!bookOrder.equals(other.bookOrder))
			return false;
		return true;
	}
	
	

}
