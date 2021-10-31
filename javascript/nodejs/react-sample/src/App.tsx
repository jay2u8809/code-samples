import React, {useState} from 'react';
import logo from './logo.svg';
import './App.css';

function App() {

  // useState
  const [title, changeTitle]: any[] = useState('Recommend Mens'); // ['Recommend Mens', function changeTitle()]
  const [contents, changeContents]: any[] = useState(['2021.10.31', '2021.10.28', '2021.10.27']);
  // inner html
  const blogTitle: string = "BLOG SAMPLE";
  const blogSubTitle = (): string => {
      return 'HELLO WORLD, My Blog';
  };
  // class, style
  const blackNav: string = 'black-nav';
  const styleObj: any = {
      fontSize: '30px'
  };

  return (
    <div className="App">
      <div className={ blackNav }>
        <div style={ styleObj }>Development Blog</div>
      </div>
        <h4>{ blogTitle }</h4>
        <h5> { blogSubTitle() } </h5>
        <div className="list">
        <h3>{ title }</h3>
        <p> { contents[0] } </p>
        <p> { contents[1] } </p>
        <p> { contents[2] } </p>
        <hr/>
        <img src={ logo } width="50%" alt="" />
      </div>
    </div>
  );
}

export default App;
