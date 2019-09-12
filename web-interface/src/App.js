import React from 'react';
import Reflux from 'reflux';

import {
    BrowserRouter as Router,
    Switch,
    Route
} from 'react-router-dom';

import Notifications from 'react-notify-toast';

import PingStore from "./stores/PingStore";
import PingActions from "./actions/PingActions";

import NavigationBar from './components/layout/NavigationBar';
import OverviewPage from "./components/overview/OverviewPage";
import NotConnectedPage from "./components/misc/NotConnectedPage";
import NotFoundPage from "./components/misc/NotFoundPage";
import AlertDetailsPage from "./components/alerts/AlertDetailsPage";
import Routes from "./util/Routes";
import Footer from "./components/layout/Footer";
import SystemPage from "./components/system/SystemPage";
import NetworksPage from "./components/networks/NetworksPage";
import NetworkDetailsPage from "./components/networks/details/NetworkDetailsPage";
import LoginPage from "./components/authentication/LoginPage";
import Store from "./util/Store";
import AuthenticationStore from "./stores/AuthenticationStore";
import AuthenticationActions from "./actions/AuthenticationActions";

class App extends Reflux.Component {

    constructor(props) {
        super(props);

        this.state = {
            apiConnected: true,
            authenticated: App._isAuthenticated()
        };

        this.stores = [PingStore, AuthenticationStore];

        App._handleLogout = App._handleLogout.bind(this);
    }

    componentDidMount() {
        const self = this;
        PingActions.ping();

        // Check if we are authenticated.
        setInterval(function () {
            self.setState({authenticated: App._isAuthenticated()});
        }, 1000);

        // Check if session is about to expire and log out if so.
        AuthenticationActions.checkSession();
        setInterval(function () {
            AuthenticationActions.checkSession();
        }, 10000);
    }

    static _isAuthenticated() {
        return Store.get("api_token") !== undefined;
    }

    static _handleLogout(e) {
        e.preventDefault();
        Store.delete("api_token");
    }

    render() {
        // TODO: This is fucked but it's currently required to hide the login page styling after initial login.
        document.body.classList.remove('login-page');
        document.body.style.backgroundImage = "";

        if(this.state.apiConnected) {
            if (this.state.authenticated) {
                return (
                    <Router>
                        <div className="nzyme">
                            <NavigationBar handleLogout={App._handleLogout} />

                            <div className="container">
                                <Notifications/>

                                <Switch>
                                    <Route exact path={Routes.DASHBOARD} component={OverviewPage}/>

                                    { /* System Status. */}
                                    <Route exact path={Routes.SYSTEM_STATUS} component={SystemPage}/>

                                    { /* Networks. */}
                                    <Route exact path={Routes.NETWORKS.INDEX} component={NetworksPage}/>

                                    { /* Networks. */}
                                    <Route exact path={Routes.NETWORKS.SHOW(":bssid", ":ssid", ":channel")}
                                           component={NetworkDetailsPage}/>

                                    { /* Alerts. */}
                                    <Route path={Routes.ALERTS.SHOW(":id")} component={AlertDetailsPage}/>

                                    { /* 404. */}
                                    <Route path={Routes.NOT_FOUND} component={NotFoundPage}/>
                                    <Route path="*" component={NotFoundPage}/> { /* Catch-all.  */}
                                </Switch>

                                <Footer/>
                            </div>
                        </div>
                    </Router>
                );
            } else {
                return (
                    <div className="nzyme">
                        <div className="container container-login">
                            <LoginPage />
                        </div>
                    </div>
                );
            }
        } else {
            return (
                <div className="nzyme">
                    <div className="container">
                        <NotConnectedPage />
                        <Footer/>
                    </div>
                </div>
            )
        }
    }
}

export default App;