import React from "react";
import {Route, Switch} from "react-router";
import Game from "./Game";
import HomePage from "./HomePage";

const Routes = (): JSX.Element => {
    
    return (
        <Switch>
            <Route path = "/games/:id" component={Game} />
            <Route path = "/" component={HomePage} />
        </Switch>
    )
}

export default Routes;