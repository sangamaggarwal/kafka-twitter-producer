package com.kafkastreamingdemo.kafkatwitterdemo.service;

import com.kafkastreamingdemo.kafkatwitterdemo.dto.TwitterEvent;

public interface LoadDataService {

    TwitterEvent loadTwitterData();
}
