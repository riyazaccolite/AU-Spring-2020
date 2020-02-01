import React, {Component} from 'react';
import { StyleSheet, Text, View, FlatList, SafeAreaView, Image } from 'react-native';
import Constants from 'expo-constants';
import { Card, Button, Icon, Badge } from 'react-native-elements';

class App extends Component {
  constructor(props) {
    super(props);
    this.state = {
      articles:[],
      loaded: false
    };
}

componentDidMount() {
  fetch("https://api.nytimes.com/svc/news/v3/content/all/all.json?api-key=b4IM3uTAnLOedTQmg7Dl1tAHxDUZgcoB")
    .then(response => {
      response.json().then(data => {
      data = data["results"].splice(0,15);
      data.forEach(element => {
          element.read = false;
          element.color = '#fcfff0';
          element.open = false;
      });
      this.setState({articles: data, loaded:true}); 
    })
      //console.log(data);
    });
}

toggleItem(index) {
  let { articles } = this.state;
  articles[index].open = articles[index].open? false: true;
  //articles.forEach((ele, ind) => console.log(ele.read + " " + ind));
  articles[index].color = '#ffcccb';
  //console.log(articles[index].read);
  //console.log(articles[index].open);
  if(!articles[index].read && !articles[index].open) { 
    articles[index].read = true;
    articles.push(articles.splice(index,1)[0]);
  }
  this.setState({ articles })
}

render() {
    // console.log('Calling render');
    return (
        <SafeAreaView style = {styles.container}>
          
        {!this.state.loaded && <Text style = {{ fontSize: 40 }}>Loading</Text>} 
          {this.state.loaded && <FlatList
              ListHeaderComponent = {<Header/>}
              stickyHeaderIndices = {[0]}
              data={this.state.articles}
              renderItem={({ item, index }) => {
                return <Card
                image={(item["thumbnail_standard"] && {uri: item["thumbnail_standard"]}) || {uri: "http://www.stjosephcte.in/sites/default/files/default_images/default-thumbnail.jpg"}}>
                  
                  {item.read && <Badge value="Read" status="error" style = {{ textAlign: "center" }}/>}
                    {!item.open && <Text style={{marginBottom: 10}}>
                      {item.title}
                    </Text>}
                    {item.open && <Text style={{marginBottom: 10, fontSize: 24, fontWeight: "bold", fontFamily: ""}}>
                      {item.title}
                    </Text>}

                {item.open && <Text style = { styles.descItem }>{item.abstract}</Text>}
                <Button
                  onPress = {() => { this.toggleItem(index) }}
                  icon={<Icon name='code' color='#ffffff' />}
                  buttonStyle={{borderRadius: 0, marginLeft: 0, marginRight: 0, marginBottom: 0}}
                  title={(!item.open && 'VIEW NOW') || 'CLOSE'} />
              </Card>
              }}
              keyExtractor={item => item.title}
          >
          </FlatList>}
        </SafeAreaView>
    );
  }
}

function Header() {
  return (
      <Text style = {styles.header}>New York Times</Text>
  );
}

function TitleItem({ item }) {
  return(
    <View style={[ styles.item, {backgroundColor: item.color, flexDirection: 'row', alignItems: "center"} ]}>
        <Image style={{width: 50, height: 50}} source={{uri: item["thumbnail_standard"]}} />
        <View style = {styles.title}>
          <Text>{item.title}</Text>
        </View>
    </View>
  );
}


const styles = StyleSheet.create({
  container: {
    flex: 1,
    marginTop: Constants.statusBarHeight,
    justifyContent: "center",
    alignItems: "center",
  },

  item: {
    padding: 20,
    marginVertical: 8,
    marginHorizontal: 16,
    borderRadius: 3,
  },
  title: {
    fontSize: 17,
    flexShrink:1,
    marginLeft: 10,
  },
  descItem: {
    padding: 20,
    marginHorizontal: 5,
    marginVertical: 5,
    fontSize: 16,
  },

  header: {
    fontSize:25,
    padding: 15,
    textAlign: "center",
    backgroundColor: 'black',
    color: 'white',
  }

});

export default App;


