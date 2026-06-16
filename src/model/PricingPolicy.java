/**
 * 
 */
package model;

/**
 * 
 */
public class PricingPolicy {

	private PricingStrategy strategy;

	public PricingPolicy(PricingStrategy strategy) {
		super();
		this.strategy = strategy;
	}

	// tinh tien
	public double calculateFee(ParkingTicket ticket, boolean hasValidSubscription) {
		if (strategy != null) {
			return strategy.calculateFee(ticket, hasValidSubscription);
		}
		return 0.0;
	}

	public PricingStrategy getStrategy() { 
		return strategy; 
	}
	public void setStrategy(PricingStrategy strategy) { 
		this.strategy = strategy; 
	}
}
