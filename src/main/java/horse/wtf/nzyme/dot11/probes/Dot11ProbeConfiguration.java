/*
 *  This file is part of nzyme.
 *
 *  nzyme is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  nzyme is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with nzyme.  If not, see <http://www.gnu.org/licenses/>.
 */

package horse.wtf.nzyme.dot11.probes;

import com.google.auto.value.AutoValue;
import horse.wtf.nzyme.configuration.Dot11NetworkDefinition;
import horse.wtf.nzyme.notifications.uplinks.graylog.GraylogAddress;

import javax.annotation.Nullable;
import java.util.List;

@AutoValue
public abstract class Dot11ProbeConfiguration {

    public abstract String probeName();

    @Nullable
    public abstract List<GraylogAddress> graylogAddresses();
    public abstract String nzymeId();

    public abstract String networkInterfaceName();
    public abstract List<Integer> channels();
    public abstract Integer channelHopInterval();
    public abstract String channelHopCommand();

    public abstract List<Dot11NetworkDefinition> getDot11Networks();

    public static Dot11ProbeConfiguration create(String probeName, List<GraylogAddress> graylogAddresses, String nzymeId, String networkInterfaceName, List<Integer> channels, Integer channelHopInterval, String channelHopCommand, List<Dot11NetworkDefinition> getDot11Networks) {
        return builder()
                .probeName(probeName)
                .graylogAddresses(graylogAddresses)
                .nzymeId(nzymeId)
                .networkInterfaceName(networkInterfaceName)
                .channels(channels)
                .channelHopInterval(channelHopInterval)
                .channelHopCommand(channelHopCommand)
                .getDot11Networks(getDot11Networks)
                .build();
    }

    public static Builder builder() {
        return new AutoValue_Dot11ProbeConfiguration.Builder();
    }

    @AutoValue.Builder
    public abstract static class Builder {
        public abstract Builder probeName(String probeName);

        public abstract Builder graylogAddresses(List<GraylogAddress> graylogAddresses);

        public abstract Builder nzymeId(String nzymeId);

        public abstract Builder networkInterfaceName(String networkInterfaceName);

        public abstract Builder channels(List<Integer> channels);

        public abstract Builder channelHopInterval(Integer channelHopInterval);

        public abstract Builder channelHopCommand(String channelHopCommand);

        public abstract Builder getDot11Networks(List<Dot11NetworkDefinition> getDot11Networks);

        public abstract Dot11ProbeConfiguration build();
    }

}