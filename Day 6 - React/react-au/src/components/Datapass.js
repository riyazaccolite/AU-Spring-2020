import React from 'react';

export default class Datapass extends React.Component{
    
    constructor(props){
        super(props);
        this.state= {
            name: "Name 1"
        }
        this.handleChange = this.handleChange.bind(this);
    }

    handleChange(event){
        this.setState({name: "Name 2"});
        console.log(event);
    }

    render(){
        return(
            <div>
                <Header />
                <h1> {this.state.name}</h1>
                <button onClick={this.handleChange}>Click this</button>
            </div>
        );
    }
}

class Header extends React.Component{
    render(){
        return(
            <div>
                Welcome to header
            </div>
        )
    }
}