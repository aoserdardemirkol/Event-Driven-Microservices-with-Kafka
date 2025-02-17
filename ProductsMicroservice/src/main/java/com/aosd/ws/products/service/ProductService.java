package com.aosd.ws.products.service;

import com.aosd.ws.products.rest.CreateProductRestModel;

public interface ProductService {

    String createProductAsync(CreateProductRestModel productRestModel);

    String createProductSync(CreateProductRestModel productRestModel) throws Exception;
}
