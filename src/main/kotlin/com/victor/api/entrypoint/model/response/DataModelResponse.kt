package com.victor.api.entrypoint.model.response

import com.fasterxml.jackson.annotation.JsonProperty

class DataModelResponse<T>(@JsonProperty("data")
                           val data: T)