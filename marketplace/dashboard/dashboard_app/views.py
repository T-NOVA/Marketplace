__author__ = "George Alexiou (TEIC)"
__email__ = "g.alexiou@pasiphae.eu"

from django.shortcuts import render_to_response


def index(request):
    return render_to_response('index.html', {})


def vnf_diagram(request, vnfd_id):
    return render_to_response('vnfdiagram.html', {'vnfd_id':vnfd_id})


def ns_diagram(request, ns_id):
    return render_to_response('nsdiagram.html', {'ns_id':ns_id})