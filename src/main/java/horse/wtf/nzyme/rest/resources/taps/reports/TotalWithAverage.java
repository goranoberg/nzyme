/*
 * This file is part of nzyme.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the Server Side Public License, version 1,
 * as published by MongoDB, Inc.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * Server Side Public License for more details.
 *
 * You should have received a copy of the Server Side Public License
 * along with this program. If not, see
 * <http://www.mongodb.com/licensing/server-side-public-license>.
 */

package horse.wtf.nzyme.rest.resources.taps.reports;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.auto.value.AutoValue;

@AutoValue
public abstract class TotalWithAverage {

    public abstract Long total();
    public abstract Long average();

    @JsonCreator
    public static TotalWithAverage create(@JsonProperty("total") Long total, @JsonProperty("average") Long average) {
        return builder()
                .total(total)
                .average(average)
                .build();
    }

    public static Builder builder() {
        return new AutoValue_TotalWithAverage.Builder();
    }

    @AutoValue.Builder
    public abstract static class Builder {
        public abstract Builder total(Long total);

        public abstract Builder average(Long average);

        public abstract TotalWithAverage build();
    }

}
