import React, {Component} from "react";
import { Navbar, NavbarBrand } from 'reactstrap';

import { Link } from "react-router-dom";

export default class AppNavbar extends Component {
    render() {
        return (
            <div>
                <Navbar color="dark" dark expand="md">
                    <NavbarBrand tag={Link} to="/">CAFÉ DA MANHÃ</NavbarBrand>
                </Navbar>
            </div>
        );
    }
}