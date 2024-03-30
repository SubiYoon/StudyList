import 'package:flutter/material.dart';

class Player {
  String? name;

  Player();
}

void main() {
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: Text("Hello Flutter!!"),
        ),
        body: Center(
          child: Text("Hello World!!"),
        ),
      ),
    );
  }
}
