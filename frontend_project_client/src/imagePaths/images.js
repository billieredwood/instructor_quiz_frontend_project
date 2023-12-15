// src/images/index.js

// Assume you have image files named "image1.jpg", "image2.jpg", etc.
const annaImages = [
    require('../AnnaImages/AnnaMain.png'),
    require('../AnnaImages/Anna1.jpeg'), // Use the `require` function to get the image path
    require('../AnnaImages/Anna2.jpeg'),
    require('../AnnaImages/AnnaDog1.jpeg'),
    require('../AnnaImages/AnnaDog2.jpeg'),
    require('../AnnaImages/AnnaDog3.jpeg'),
    // Add more image paths as needed
  ];

  const colinImages = [
    require('../ColinImages/ColinMain.png'),
    require('../ColinImages/Colin1.png'),
    require('../ColinImages/Colin2.jpg'),
    require('../ColinImages/Colin3.jpg'),
    require('../ColinImages/Colin4.jpg'),
    require('../ColinImages/Colin5.jpg'),
    require('../ColinImages/Colin6.jpg'),
    require('../ColinImages/Colin7.jpg'),
    require('../ColinImages/ColinPet1.jpg'),
    require('../ColinImages/ColinPet2.jpg'),
    require('../ColinImages/ColinPet3.jpg')
  ]

  const thibyaaImages = [
    require('../ThibyaaImages/ThibyaaMain.png'),
    require('../ThibyaaImages/Thibyaa1.jpg'),
    require('../ThibyaaImages/Thibyaa2.jpg'),
    require('../ThibyaaImages/Thibyaa3.jpg'),
    require('../ThibyaaImages/Cat1.jpg'),
    require('../ThibyaaImages/Cat2.jpg'),
  ]

  const zsoltImages = [
    require('../ZsoltImages/ZsoltMain.png'),
    require('../ZsoltImages/Zsolt1.png'),
    require('../ZsoltImages/Zsolt2.png'),
    require('../ZsoltImages/Zsolt3.jpg'),
    require('../ZsoltImages/Zsolt4.png'),
    require('../ZsoltImages/Zsolt5.png'),
    require('../ZsoltImages/ZsoltDog1.jpg'),
    require('../ZsoltImages/ZsoltDog2.jpg'),
    require('../ZsoltImages/ZsoltHedgehog1.jpg')
  ]
  
  module.exports = {annaImages, zsoltImages, thibyaaImages, colinImages};

  