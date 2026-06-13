/**
 * 
 */
package model;

import java.time.LocalDateTime;

/**
 * 
 */
public class PricingPolicy {
	public static final double DEFAULT_HOURLY_RATE = 10000.0;
	public static final long MININUM_CHARGE_HOURS = 1;

	private PricingStragety strategy;

	/**
	 * @param strategy
	 */
	public PricingPolicy(PricingStragety strategy) {
		super();
		this.strategy = strategy;
	}

	public PricingStragety getStrategy() {
		return strategy;
	}

	public void setStrategy(PricingStragety strategy) {
		this.strategy = strategy;
	}

}
