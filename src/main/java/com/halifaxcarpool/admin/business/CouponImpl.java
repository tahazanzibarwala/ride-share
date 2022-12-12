package com.halifaxcarpool.admin.business;
import com.halifaxcarpool.admin.business.beans.Coupon;
import com.halifaxcarpool.admin.database.dao.dao.ICouponDao;

import java.time.LocalDate;
import java.util.List;

public class CouponImpl implements ICoupon {
    @Override
    public boolean createCoupon(Coupon coupon, ICouponDao couponDao) {
        return couponDao.createCoupon(coupon);
    }

    @Override
    public List<Coupon> viewCoupons(ICouponDao iCouponDao) {

        return iCouponDao.viewCoupons();
    }

    @Override
    public void deleteCoupon(int couponId, ICouponDao couponDao) {
        couponDao.deleteCoupon(couponId);
    }


    @Override
    public Double getMaximumDiscountValidToday(ICouponDao couponDao) {
        return couponDao.getMaximumDiscount();
    }
}