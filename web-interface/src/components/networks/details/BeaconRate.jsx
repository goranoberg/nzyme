import React from 'react'

import numeral from 'numeral'

class BeaconRate extends React.Component {
  render () {
    return (
            <span>
                {numeral(this.props.rate.rate).format('0.0')}
            </span>
    )
  }
}

export default BeaconRate
