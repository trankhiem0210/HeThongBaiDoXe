/**
 * 
 */
package model;

/**
 * 
 */
public interface PricingStrategy {
	double calculateFee(ParkingTicket ticket, boolean hasValidSubscription);
}
