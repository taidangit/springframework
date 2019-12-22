import React, { Component } from 'react';
import { Link } from 'react-router-dom';
import PropTypes from 'prop-types';
import { connect } from 'react-redux';
import { logout } from './../../actions/securityActions';

class Header extends Component {

    constructor(props) {
        super(props);

        this.handleLogout = this.handleLogout.bind(this);
    }
    
    handleLogout() {
        this.props.logout(this.props.history);
    }

    render() {
        const {validToken, user} = this.props.security;

        const userIsAuthenticated = (
            <div className="collapse navbar-collapse" id="mobile-nav">
                <ul className="navbar-nav mr-auto">
                    <li className="nav-item">
                        <Link to="/dashboard" className="nav-link">
                            Dashboard
                        </Link>
                    </li>
                </ul>

                <ul className="navbar-nav ml-auto">
                    <li className="nav-item">
                        <Link className="nav-link" to="/updateUserInfo">
                            <i className="fa fa-user-circle mr-1"></i>{user.fullName}
                        </Link>
                    </li>
                    <li className="nav-item">
                        <Link className="nav-link" to="" onClick={this.handleLogout}>
                            <i className="fa fa-sign-out mr-1"></i>Logout
                        </Link>
                    </li>
                </ul>
            </div>
        );

        const userIsNotAuthenticated = (
            <div className="collapse navbar-collapse" id="mobile-nav">
               
                <ul className="navbar-nav ml-auto">
                    <li className="nav-item">
                        <Link className="nav-link" to="/register">
                            Sign Up
                        </Link>
                    </li>
                    <li className="nav-item">
                        <Link className="nav-link" to="/login">
                        <i className="fa fa-sign-in mr-1"></i>Login
                        </Link>
                    </li>
                </ul>
            </div>
        );

        let headerLinks = null;
        if(validToken) {
            headerLinks = userIsAuthenticated;
        } else {
            headerLinks = userIsNotAuthenticated;
        }

        return (
           
            <nav className="navbar navbar-expand-sm navbar-dark bg-primary mb-4 fixed-top">
                <div className="container">
                    <Link to="/" className="navbar-brand" >
                        Personal Project Management Tool
                    </Link>
                    <button className="navbar-toggler" type="button" data-toggle="collapse" data-target="#mobile-nav">
                        <span className="navbar-toggler-icon" />
                    </button>

                    {headerLinks}
                    
                </div>
            </nav>
           
            
        );
    }
}

Header.propTypes = {
    logout: PropTypes.func.isRequired,
    security: PropTypes.object.isRequired
}

const mapStateToProps = state => ({
    security: state.security
});

export default connect(mapStateToProps, {logout})(Header);

