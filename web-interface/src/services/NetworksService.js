import RESTClient from '../util/RESTClient'
import { notify } from 'react-notify-toast'

class NetworksService {
  findAll (filter) {
    const self = this

    RESTClient.get('/networks/bssids', {}, function (response) {
      let bssids = {}

      if (filter) {
        filter = filter.toLowerCase()
        Object.keys(response.data.bssids).forEach(function (key) {
          const bssid = response.data.bssids[key]

          // Check if BSSID matches filter.
          if (bssid.bssid.toLowerCase().includes(filter)) {
            bssids[key] = bssid
            return
          }

          // Check if OUI matches filter.
          if (bssid.oui.toLowerCase().includes(filter)) {
            bssids[key] = bssid
            return
          }

          // Check if any SSID matches filter.
          Object.keys(bssid.ssids).forEach(function (ssid) {
            if (bssid.ssids[ssid].toLowerCase().includes(filter)) {
              bssids[key] = bssid
            }
          })
        })
      } else {
        bssids = response.data.bssids
      }

      self.setState({ bssids: bssids })
    })
  }

  findSSIDOnBSSID (bssid, ssid, includeHistory = false, historySeconds = 10800) {
    const self = this

    RESTClient.get('/networks/bssids/' + encodeURIComponent(bssid) + '/ssids/' + encodeURIComponent(ssid), { include_history: includeHistory, history_seconds: historySeconds }, function (response) {
      self.setState({ ssid: response.data })
    })
  }

  onFindSSID (ssid, successCallback) {
    const self = this
    RESTClient.get('/networks/ssids/' + encodeURIComponent(ssid), {}, successCallback, function (error) {
      if (error.response && error.response.status === 404) {
        self.setState({ globalSSID: undefined, notFound: true })
      }
    })
  }

  resetNetworks (successCallback) {
    RESTClient.post('/networks/reset/', {}, function () {
      successCallback()
      notify.show('Networks have been reset.', 'success')
    })
  }
}

export default NetworksService
