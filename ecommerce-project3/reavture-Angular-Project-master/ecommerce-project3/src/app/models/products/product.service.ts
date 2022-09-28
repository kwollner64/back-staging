import { getLocaleNumberFormat } from "@angular/common";
import { Injectable } from "@angular/core";
import { Product } from "./product.model"
import { HttpClient } from '@angular/common/http';

@Injectable({
    providedIn: 'app-root' 
})
export class ProductService{
    allProducts: Product[] = [{
    sku: 1,
    productQuantity: 10,
    productName: "Golf clubs",
    productImage: "https://images.unsplash.com/photo-1526166845602-7939d79a78d8?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=870&q=80",
    productPrice: 650
    },
    {
    sku: 2,
    productQuantity: 1,
    productName: "Globe",
    productImage: "https://www.valuetrend.ca/wp-content/uploads/2017/10/globe.jpg",
    productPrice: 20
    },
    {
    sku: 3,
    productQuantity: 4,
    productName: "Hiking shoes",
    productImage: "https://image.peltzshoes.com/images/products/1_2140322_ZM.jpg",
    productPrice: 90
    },
    {
    sku: 4,
    productQuantity: 3,
    productName: "Halloween costume",
    productImage: "https://cdn.shopify.com/s/files/1/0653/1189/9895/products/product-image-1145915401_0ac43c78-e4a4-4314-8383-37898022df44_1280x.jpg?v=1657363692",
    productPrice: 30
    }
];

baseUrl: string = "http://localhost:8080/api/products"

constructor(private HttpClient: HttpClient){}

getAllProducts(): Product[]{
    return this.HttpClient.get<Product[]>(this.baseUrl);
}
}