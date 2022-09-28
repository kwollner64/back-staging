import { Order } from "../orders/order.model";

export interface orderItem{
    itemId: number;
    orderNo: number;
    productSku: number;
    productQty: number;
    productPrice: number;
    order: Order;
}