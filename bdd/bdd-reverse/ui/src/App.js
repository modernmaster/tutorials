import logo from './logo.svg';
import './App.css';
import React, {useEffect, useState} from 'react'
import Container from 'react-bootstrap/Container'
import Row from 'react-bootstrap/Row'


const initialBooks = {
    books: {
        book1: false,
        book2: false,
        book3: false,
        book4: false,
        book5: false
    }
}
const initialCost = {
    cost: 0
}
const App = () => {

    const [books, setBooks] = useState(initialBooks)
    const [total, setCost] = useState(initialCost)

    const postData = async () => {
        const response = await fetch('https://bookstore.io/bookstore/checkout',
            {
                method: 'POST',
                body: JSON.stringify(books),
                headers: {
                    'Content-Type': 'application/json'
                },
            });
        const cost = await response.json();
        setCost((
            {cost: cost}));
    }

    useEffect(() => {
        postData()
    }, [])

    const handleSubmit = event => {
        event.preventDefault();
        postData();
    }

    const handleCheck = event => {
        event.preventDefault();
        setBooks(({books}) => ({
            books: {
                ...books,
                [event.target.id]: event.target.checked
            }
        }));
    }

    return (
        <div className="App">
            <header className="App-header">
                <img src={logo} className="App-logo" alt="logo"/>
                <Container>

                    <div className="App-intro">
                        <h2 style={{color: 'white'}}>Book List</h2>
                        <hr></hr>
                        <Row className="row justify-content-center">
                            <form onSubmit={handleSubmit}>
                                <fieldset>
                                    <p><label>Book 1</label><input id="book1" type="checkbox" onInput={handleCheck}/></p>
                                    <p><label>Book 2</label><input id="book2" type="checkbox" onInput={handleCheck}/></p>
                                    <p><label>Book 3</label><input id="book3" type="checkbox" onInput={handleCheck}/> </p>
                                    <p><label>Book 4</label><input id="book4" type="checkbox" onInput={handleCheck}/></p>
                                    <p><label>Book 5</label><input id="book5" type="checkbox" onInput={handleCheck}/></p>
                                </fieldset>
                                <button id= "calculate" type="submit">Calculate</button>
                            </form>
                        </Row>
                        <p id="cost">
                            Total Cost: {total.cost} EUR
                        </p>
                    </div>

                </Container>

            </header>
        </div>
    )
}
export default App;
