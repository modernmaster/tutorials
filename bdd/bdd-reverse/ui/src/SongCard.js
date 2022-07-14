import React from 'react'
import Card from 'react-bootstrap/Card'


const SongCard = (props) => {

    const { song } = props
    console.log(song)
    return (
        <Card bg='light'style={{width:'18rem'}}>
            <Card.Header>{song.title}</Card.Header>
            <Card.Body>
                <Card.Title>{song.artists}</Card.Title>
                <Card.Text>Some quick example text to build on the card title and make up the bulk of the card's content.</Card.Text>
            </Card.Body>
        </Card>
    )
}
export default SongCard;