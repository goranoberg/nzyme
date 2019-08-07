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

package horse.wtf.nzyme.rest.responses.networks;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.auto.value.AutoValue;
import horse.wtf.nzyme.dot11.networks.SignalDelta;
import horse.wtf.nzyme.dot11.networks.beaconrate.AverageBeaconRate;
import horse.wtf.nzyme.dot11.networks.sigindex.AverageSignalIndex;
import horse.wtf.nzyme.dot11.networks.sigindex.SignalInformation;

import javax.annotation.Nullable;
import java.util.List;

@AutoValue
public abstract class ChannelResponse {

    @JsonProperty("signal_history")
    @Nullable
    public abstract List<SignalInformation> signalHistory();

    @JsonProperty("channel_number")
    public abstract int channelNumber();

    @JsonProperty("bssid")
    public abstract String bssid();

    @JsonProperty("ssid")
    public abstract String ssid();

    @JsonProperty("total_frames")
    public abstract long totalFrames();

    @JsonProperty("fingerprints")
    public abstract List<String> fingerprints();

    @JsonProperty("signal_index")
    public abstract double signalIndex();

    @JsonProperty("signal_index_threshold")
    public abstract AverageSignalIndex signalIndexThreshold();

    @JsonProperty("signal_index_status")
    public abstract String signalIndexStatus();

    @JsonProperty("expected_delta")
    public abstract SignalDelta expectedDelta();

    public static ChannelResponse create(List<SignalInformation> signalHistory, int channelNumber, String bssid, String ssid, long totalFrames, List<String> fingerprints, double signalIndex, AverageSignalIndex signalIndexThreshold, String signalIndexStatus, SignalDelta expectedDelta) {
        return builder()
                .signalHistory(signalHistory)
                .channelNumber(channelNumber)
                .bssid(bssid)
                .ssid(ssid)
                .totalFrames(totalFrames)
                .fingerprints(fingerprints)
                .signalIndex(signalIndex)
                .signalIndexThreshold(signalIndexThreshold)
                .signalIndexStatus(signalIndexStatus)
                .expectedDelta(expectedDelta)
                .build();
    }

    public static Builder builder() {
        return new AutoValue_ChannelResponse.Builder();
    }

    @AutoValue.Builder
    public abstract static class Builder {
        public abstract Builder signalHistory(List<SignalInformation> signalHistory);

        public abstract Builder channelNumber(int channelNumber);

        public abstract Builder bssid(String bssid);

        public abstract Builder ssid(String ssid);

        public abstract Builder totalFrames(long totalFrames);

        public abstract Builder fingerprints(List<String> fingerprints);

        public abstract Builder signalIndex(double signalIndex);

        public abstract Builder signalIndexThreshold(AverageSignalIndex signalIndexThreshold);

        public abstract Builder signalIndexStatus(String signalIndexStatus);

        public abstract Builder expectedDelta(SignalDelta expectedDelta);

        public abstract ChannelResponse build();
    }
}