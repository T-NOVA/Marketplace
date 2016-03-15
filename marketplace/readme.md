# Marketplace Deployment Info

## Docker installation info

```sh
$ sudo apt-key adv --keyserver hkp://keyserver.ubuntu.com:80 --recv-keys 36A1D7869245C8950F966E92D8576A8BA88D21E9
$ sudo sh -c "echo deb https://get.docker.io/ubuntu docker main > /etc/apt/sources.list.d/docker.list"
$ sudo apt-get update
$ sudo apt-get install lxc-docker python-pip
$ sudo pip install docker-compose
```

## Deployment

```sh
$ cd ~
$ git clone https://github.com/T-NOVA/Marketplace.git
$ cd Marketplace/marketplace
# build cyclops base image
$ sudo docker build -t cyclops-base cyclops/docker-files/cyclops-base/.
$ sudo docker-compose up
```

Visit http://localhost/
