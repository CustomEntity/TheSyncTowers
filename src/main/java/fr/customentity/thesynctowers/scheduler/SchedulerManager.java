package fr.customentity.thesynctowers.scheduler;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import fr.customentity.thesynctowers.TheSyncTowers;

import java.util.ArrayList;
import java.util.List;

/**
 *  Copyright (c) 2021. By CustomEntity
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 *  The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * @Author: CustomEntity
 * @Date: 26/02/2021
 *
 */

@Singleton
public class SchedulerManager  {

    private List<Scheduler> schedulers = new ArrayList<>();

    public List<Scheduler> getSchedulers() {
        return schedulers;
    }

    public void setSchedulers(List<Scheduler> schedulers) {
        this.schedulers = schedulers;
    }
}