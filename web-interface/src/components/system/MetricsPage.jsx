import React from 'react'

import Metrics from './metrics/Metrics'

class MetricsPage extends React.Component {
  render () {
    return (
            <div>
                <div className="row">
                    <div className="col-md-12">
                        <h1>Metrics</h1>
                    </div>
                </div>

                <div className="row mt-3">
                    <div className="col-md-12">
                        <div className="card">
                            <div className="card-body">
                                <Metrics />
                            </div>
                        </div>
                    </div>
                </div>
            </div>
    )
  }
}

export default MetricsPage;
