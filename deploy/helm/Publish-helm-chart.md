## Prerequisites

- Helm V3: [Install Helm V3](https://helm.sh/docs/intro/install/)
- AWS CLI: [Install AWS CLI](https://docs.aws.amazon.com/cli/latest/userguide/getting-started-install.html)
- AWS CLI Configure: [Configure AWS credential](https://docs.aws.amazon.com/cli/latest/userguide/cli-configure-quickstart.html) 

## Initialize, Package and publish charts in the Amazon S3 Helm repository

* Create S3 bucket for Helm chart (naming as `helm.bizBrainz.com` \- Hosting S3 as Static web requires bucket name be the same with the domain\)
* Clone your Helm charts (ignore if already have Bizbrainz repo on machine)
* Package the local Helm chart

```
helm package ./deploy/helm
```

* Store the local package in the Amazon S3 Helm repository

```
aws s3 cp ./bizBrainz-1.4.1.tgz s3://helm.bizBrainz.com/
```

* Initialize the Amazon S3 Helm repository

```
helm repo index . --url https://helm.bizBrainz.com
```

* Upload `index.yaml` to S3 bucket

```
aws s3 cp ./index.yaml s3://helm.bizBrainz.com
```

* Verify the newly created Helm repository by checking that the `index.yml` file was created in the S3 bucket

```
aws s3 ls s3://helm.bizBrainz.com
```

## Search for and install a chart from the Amazon S3 Helm repository

* Add Helm repo with S3 bucket URL (or Helm URL `https://helm.bizBrainz.com`)

```
helm repo add bizBrainz http://helm.bizBrainz.com
```

* Search for all versions of the my-app chart. Run following command to search all available version of Helm chart

```
helm search repo bizBrainz --versions 
```

* Install a chart from the Amazon S3 Helm repository

```
helm install bizBrainz bizBrainz/bizBrainz --version 1.4.1
```

## Upgrade your Helm repository (If need)

* Modify the chart
* Package Helm chart

```
helm package ./deploy/helm
```

* Push the new version to the Helm repository in Amazon S3

```
aws s3 cp ./bizBrainz-1.4.1.tgz s3://helm.bizBrainz.com
```

* Create index file

```
helm repo index --url http://helm.bizBrainz.com
```

* Push new `index.yaml` file into S3 bucket

```
aws s3 cp ./index.yaml s3://helm.bizBrainz.com
```

* Verify the updated Helm chart

```
helm repo update

helm search repo bizBrainz
```



## Install Bizbrainz Helm Chart Using Helm URL

* Add Helm repo

```
helm repo add bizBrainz http://helm.bizBrainz.com
```

* Update Helm repo

```
helm repo update
```

* Install Helm chart

```
helm install bizBrainz bizBrainz/bizBrainz
```
